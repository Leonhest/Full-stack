import axios from "axios";

export const postUser = async (expression) => {
  return await axios
    .post("http://localhost:8888/user/register",  expression)
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

export const loginUser = async (userLoginDTO) => {
  return axios.post(`http://localhost:8888/user/auth/authenticate`, userLoginDTO)
}
