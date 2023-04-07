import axios from "axios";
import store from '../store';
import SessionToken from '@/features/SessionToken.js'

export const postCalculation = async (expression) => {
  return await axios
    .post("http://localhost:8888/calculate",  expression, {
      headers: {
        Authorization: `Bearer ${await SessionToken()}`,
      }
        })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      if (error.response) {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        console.log(error.request);
      } else {
        console.log('Error', error.message);
      }
      return error.data

    });
};

export const getCalculations = async (expression) => {
  return await axios
    .post("http://localhost:8888/calculate/log",  expression, {
      headers: {
        Authorization: `Bearer ${await SessionToken()}`,
      }
    })
    .then((response) => {
      response.data.forEach(element => {
        let exp = element.expression + "=" + element.result
        store.commit("addLog", exp)
      });
   
      return response.data;
    })
    .catch((error) => {
      if (error.response) {
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        console.log(error.request);
      } else {
        console.log('Error', error.message);
      }
      return error.data

    });
};