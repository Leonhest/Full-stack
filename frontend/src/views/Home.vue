<script setup>
import Calculator from "@/components/Calculator.vue";
import Log from "@/components/Log.vue";
</script>


<template>
  <header>
  </header>

  <main>
    <Calculator />
    <br>
    <textarea disabled id="log">LOG</textarea>
    <Log/>
  </main>
</template>

<style scoped>

main{
  display: grid;
  place-items: center;
  padding: 50px;

}
#log{
  width: 300px;
  height: 50px;
  text-align: center;
  font-size: 35px;
  background-color: #222222;
  color: white;
  font-family: "Arial Black";
  border-radius: 35px;
  margin-bottom: 3px;
  resize: none;

}
</style>
