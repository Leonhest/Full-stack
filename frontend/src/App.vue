<script setup>


</script>


<template>
  <v-app>
  <div>
    
    <div id="nav" v-if="!['LogIn', 'Register'].includes($route.name)">
      <router-link class="navButton" to="/home">Home</router-link>
      <span></span>
      <router-link class="navButton" to="/contact">Contact</router-link>
      <span></span>
    </div>
    <router-view />
  </div>
</v-app>
</template>

<style scoped>
#nav{
  display: flex;
  place-content: center;

}



.navButton{
  text-decoration-line: none;
  font-size: 30px;
  font-family: "Arial Black";
  color: #222222;
  width: 150px;
  height: 50px;
  line-height: 45px;
  text-align: center;
  background-color: white;
  margin: 4px;
  border-style: solid;
  border-color: #222222;
  border-radius: 50px;
  border-width: 3px;
}


.navButton:hover{
  color: white;
  background-color: #222222;
  transition-duration: 0.5s;
  scale: 95%;

}

</style>
