<template>
    <div id="contact">
    <h1>LogIn</h1>
      <hr>
    <DynamicForm
        :schema="schema"
        @formSub="formSub"
    />
    <span></span>
      <router-link id="regButton" to="/register">Register new user</router-link>
      <span></span>
    </div>
    
  </template>
  
  <script>
  import DynamicForm from "@/components/DynamicForm.vue";
  import * as Yup from "yup";
  import { loginUser} from "../services/user";
  
  
  export default {
    components: {DynamicForm},
    data (){
      return{
        schema:[
            {
              name: "username",
              as: "input",
              type: "text",
              placeholder: "username",
              rules: Yup.string().required(),
            },
            {
              name: "password",
              as: "input",
              type: "text",
              placeholder: "password",
              rules: Yup.string().required(),
            },
  
        ],
      }
      },
    methods:{

      formSub(variable){
    const userLoginDTO = {
      "username": variable.username,
      "password": variable.password
    }
        loginUser(userLoginDTO).then(async response =>{
            if(response !== undefined){
              this.$store.commit("setSessionToken", response.data.token)
              const username = Object.values(variable)[0]
                this.$store.commit("addUsername", username)
                this.$store.commit("LogIn")
                this.$router.push({name: 'Home'})
            }
            else{
                alert("Wrong Username or Password")
            }
        }
            
        )
        
      },


    },
  }
  </script>
  
  <style scoped>
  #contact{
    display: grid;
    place-content: center;
    text-align: center;
    font-family: "Arial Black";
  }
  #regButton{
    color: blue;
    text-decoration: none;
    
  }
  form{
    display: grid;
    place-content: center;
    place-items: center;
    grid-gap: 20px;
    margin-top: 30px;
  
  }
  form button{
    place-items: center;
    width: 100px;
  }
  hr{
    width: 400px;
    background-color: #222222;
    border-width: 0;
    height: 2px;
  }
  </style>