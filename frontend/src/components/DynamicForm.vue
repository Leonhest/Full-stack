<template>
  <Form @submit="onSubmit">
    <div id="formSection"
        v-for="{name, as, children,...attrs} in schema" :key="name">
      <label :for="name">{{ name }}</label>
      <Field class="formField"
             :as="as"
             :id="name"
             :name="name"
             v-bind="attrs"
           />
      <ErrorMessage id="errorMessage" :name="name"/>
    </div>

    <button id="submitButton" type="submit">Submit</button>
  </Form>
</template>

<script>
import { Form, Field, ErrorMessage } from 'vee-validate';
import axios from "axios";
export default {
  name: 'DynamicForm',
  components: {
    Form,
    Field,
    ErrorMessage
  },
  props: {
    schema: {
      type: Object,
      required: true,
    },
  },
  methods:{
    onSubmit(values){
            this.$emit("formSub", values)
    }
  },



};
</script>

<style scoped>
.formField{
  font-size: 20px;
  font-family: "Arial";
  width: 300px;
  line-break: auto;
  resize: none;
  border-width: 2px;
  border-style: solid;
  padding: 4px;
  border-radius: 5px;
}
label{
  font-size: 25px;
  font-family: "Arial Black";
  text-align: center;
}

#formSection{
  display: grid;
  grid-gap: 5px;

}

#errorMessage{
  font-family: Arial;
  font-size: 15px;
  color: red;
}

form button{
  width: 100px;
  height: 30px;
  cursor: pointer;
}

#Message{
  height: 200px;
}

#submitButton{
  display: flex;
  place-content: center;
  text-decoration-line: none;
  font-size: 20px;
  font-family: "Arial Black",serif;
  color: #222222;
  width: 100px;
  height: 50px;
  line-height: 40px;
  text-align: center;
  background-color: white;
  margin: 4px;
  border-style: solid;
  border-color: #222222;
  border-radius: 50px;
  border-width: 3px;
}

#submitButton:hover{
  color: white;
  background-color: #222222;
  transition-duration: 0.5s;
  scale: 95%;
}
</style>