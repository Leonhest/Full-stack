<template>
    <div id="contact">
    <h1>Register</h1>
      <hr>
    <DynamicForm
        :schema="schema"
        @formSub="formSub"
    />
    </div>
    
  </template>
  
  <script>
  import DynamicForm from "@/components/DynamicForm.vue";
  import * as Yup from "yup";
import router from "../router";
import { postUser } from "../services/user";
  
  
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
        postUser(variable).then(
            this.$router.push({name: 'LogIn'})
        )
        
      }
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