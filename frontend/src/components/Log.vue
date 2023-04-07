<template>
  <div>
<v-data-table
          :headers="headers"
          :items="log_array"
          :hide-default-footer="true"
          :page.sync="pagination.page"
        >
        </v-data-table>
      </div>
<v-pagination
  v-model="pagination.page"
  :length="log_length"
  :total-visible = "pagination.visible"
></v-pagination>

</template>

<script>
export default {
  data () {
  return {
    headers: [
        {
        text: 'Column',
        value: 'item',
        align: 'middle'
        },
      ],
    
    pagination: {
      page: 1,
      visible: 10
    }
  }
},
methods:{
  handlePagination(value) {
  console.log(this.$store.state.logs.logArr)
}
},
  computed:{
    log_output() {
      let list = this.$store.state.logs.logArr;
      let input = "";
      list.forEach(value => {
        input += value + "\n"
      })

      return input
    },

    log_length(){
      return Math.ceil(this.$store.state.logs.logArr.length/10)
    },

    log_array(){
      return this.$store.state.logs.logArr.map(item => ({ item }));
    }
  }
}
</script>

<style scoped>
textarea{
  width: 300px;
  height: 300px;
  text-align: center;
  font-size: 25px;
  background-color: #222222;
  font-family: "Arial Black";
  color: white;
  border-radius: 35px;
  resize: none;
}

div{
  width: 400px;
}

</style>