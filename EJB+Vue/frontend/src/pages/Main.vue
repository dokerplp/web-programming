<template>
  <div>
    <Header
        :login="login"
    />
    <Graph
        :login="login"
        :pass="pass"
        :reg="registered"
        @table="table"
    />
    <Table
        ref="results"
    />
  </div>
</template>

<script>
import Graph from "@/components/mainPage/graphSection/Graph";
import Table from "@/components/mainPage/tableSection/Table";
import Header from "@/components/mainPage/headerSection/Header";

export default {
  name: "Main",
  components: {Header, Table, Graph},
  data() {
    return {
      login: this.$route.params.login,
      pass: this.$route.params.pass,
      registered: this.$route.params.registered
    }
  },
  methods: {
    table(data) {
      this.$refs.results.setUp(data.data, data.status, data.data)
    }
  }
}
</script>

<style scoped>
#left {
  float: left;
  width: 35%;
}

#right {
  float: left;
  width: 65%;
}
</style>