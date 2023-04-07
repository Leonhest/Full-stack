<template>
  <div id="mainCalc">
    <textarea aria-label="display" disabled>{{input_string}}</textarea>

    <div id = "calcButtonsDiv">
      <div id = "numberButtonsDiv">
        <button class="topButtons" @click="deleteValue">Del</button>
        <button class="topButtons" @click="clear">C</button>
        <button class="topButtons" @click="inputAns">Ans</button>
        <button class="numberButtons" v-for="number in numbers" @click="add_value(number)">{{ number }}</button>
        <button id= "zeroButton" class="numberButtons"  @click="add_value(0)">0</button>
        <button class="numberButtons" @click="add_value('.')">.</button>

      </div>
          <div id = "operatorsDiv"><button class="operators" v-for="operator in operators" @click="add_value(operator)">{{ operator }}</button>
          <button class = "operators" @click="calculate">=</button>

      </div>

    </div>


  </div>
</template>

<script>
import Log from "@/components/Log.vue";
import { postCalculation } from "@/services/calc";
import { getCalculations } from "@/services/calc";


export default {
  data() {
    return {
      inputValue: 0,
      operators: ['+', '-', 'x', '/'],
      numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9],
      calculation: [],
      answer: 0,
      finished: false
    };
  },
  methods: {
    add_value(value) {
          if (this.finished){
            this.finished = false;
            this.calculation = []
            this.calculation.push(value)
          }
          else if(this.calculation.length === 0){
            this.calculation.push(value);
          }
          else if(this.calculation.length === 1 && this.calculation[0] === "-"){
            this.calculation[0] = -value
          }
          else if(value === "+" || value === "-" || value === "x" || value === "/"){
            this.calculation.push(value)
          }
          else if(this.calculation[this.calculation.length - 1] === "+"
              || this.calculation[this.calculation.length - 1] === "-"
              || this.calculation[this.calculation.length - 1] === "x"
              || this.calculation[this.calculation.length - 1] === "/"){
            this.calculation.push(value);
          }
          else {
            this.calculation[this.calculation.length-1] += String(value)
          }


      },
    deleteValue(){
      this.calculation.pop();
    },
    clear(){
      this.calculation = [];
    },
    inputAns(){
      if (this.finished){
        this.finished = false;
        this.calculation = []
        this.calculation.push(this.answer)
      }
      else this.calculation.push(this.answer);

    },
    async calculate(){
      if(this.calculation.length < 3) return;
      let result = await this.sendExp({
        expression: this.calculation,
        username: this.$store.state.user.username
      });
      let logstring = "";
      this.calculation.forEach(value => {
        logstring += value
      })

      logstring += "=" + result;
      this.$store.commit("addLog", logstring)
      this.answer = result;
      this.calculation = [result];
      this.finished = true;
      
      
    },

    async sendExp(exp){
      return postCalculation(exp)
    },
    },
  beforeMount() {
    getCalculations({
        username: this.$store.state.user.username
      })
  },
  computed: {
    input_string() {
      let input = "";
      this.calculation.forEach(value => {
        input += value
      })

      return input
    }
  }

};
</script>

<style scoped>

#mainCalc{
  display: grid;
  width: 300px;
  height: 469px;
  grid-template-rows: 1fr 4fr;
  border-width: 0px;
  border-style: solid;
  border-color: white;
  background-color: #222222;
  border-radius: 35px;
  padding: 3px;
}

textarea{
  background-color: #222222;
  border-width: 0;
  resize: none;
  color: white;
  border-radius: 35px;
  text-align: right;
  font-size: 300%;
  padding: 10px;

}
#calcButtonsDiv{
  display: grid;
  grid-template-columns: 3fr 1fr;
}

#calcButtonsDiv button{
  display: grid;
  margin: 4px;
  place-items: center;

  border-radius: 50%;
  border-width: 0;
  font-size: 30px;
  font-family: "Helvetica",serif;

}

#calcButtonsDiv button:active{
  background-color: white;
}

#calcButtonsDiv button:hover{
  cursor: pointer;
}

#operatorsDiv{
  display: grid;
}
.operators{
  background-color: orange;
  color: white;
}

#numberButtonsDiv{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;

}
.topButtons{
  background-color: lightgray;
}

.numberButtons{
  background-color: gray;
  color: white;
}
#calcButtonsDiv #zeroButton {
  grid-column: span 2;
  border-radius: 100px;

}

</style>


