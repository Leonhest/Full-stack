<template>
  <div id="contact">
  <h1>Contact</h1>
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
import { postFeedback } from "../services/contact";


export default {
  components: {DynamicForm},
  data (){
    return{
      schema:[
          {
            name: "Name",
            as: "input",
            type: "text",
            placeholder: "name",
            rules: Yup.string().required(),
          },
          {
            name: "Email",
            as: "input",
            type: "email",
            placeholder: "e-mail",
            rules: Yup.string().email().required(),
          },
          {
            name: "Message",
            as: "textarea",
            type: "text",
            placeholder: "message",
            rules: Yup.string().required(),
          },

      ],
    }
    },
  methods:{
    formSub(variable){
      postFeedback(variable)
      const name = Object.values(variable)[0]
      const email = Object.values(variable)[1]
      this.$store.commit("addName", name)
      this.$store.commit("addEmail", email)
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