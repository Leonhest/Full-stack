import axios from "axios";
import SessionToken from '@/features/SessionToken.js'

export const postFeedback = async (expression) => {
  return await axios
    .post("http://localhost:8888/user/register",  expression, {
      headers: {
        Authorization: `Bearer ${await SessionToken()}`,
      }
    }
    )
    .then((response) => {
        alert("success")
      return response.data;
    })
    .catch((error) => {
      if (error.response) {
        alert(error.response.status)
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        alert(error.request)
        console.log(error.request);
      } else {
        alert(error.message)
        console.log('Error', error.message);
      }
      return error.data

    });
};