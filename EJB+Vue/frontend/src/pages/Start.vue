<template>
  <div>
    <Author/>
    <Body/>
  </div>
</template>

<script>
import Author from "@/components/startPage/authorSection/Author";
import Body from "@/components/startPage/bodySection/Body";
export default {
  name: "Start",
  components: {Body, Author}
}
</script>

<style scoped>

</style>