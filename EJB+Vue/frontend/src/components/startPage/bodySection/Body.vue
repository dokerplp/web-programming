<template>
  <div>
    <Time/>
    <Login/>
  </div>
</template>

<script>
import Login from "@/components/startPage/bodySection/loginSection/Login";
import Time from "@/components/startPage/bodySection/timeSection/Time";
export default {
  name: "Body",
  components: {Time, Login}
}
</script>

<style scoped>
#left {
  float: left;
  width: 65%;
}

#right {
  float: left;
  width: 35%;
}
</style>