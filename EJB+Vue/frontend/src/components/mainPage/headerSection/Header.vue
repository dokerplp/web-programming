<template>
  <div id="header">
    <div>
      {{login}}
    </div>
    <div>
      Welcome to the page!
    </div>
  </div>
</template>

<script>
module.exports = {
  props: {
    login: {
      type: String,
      default() {
        return "Undefined"
      }
    }
  }
}
</script>

<style scoped>
#header div:first-child {
  background: #ad0aad;
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin-right: 20px;
  margin-left: 20px;
  margin-top: 20px;
}

#header div:last-child {
  background: #d760d7;
  font-size: 25px;
  border-radius: 0 0 8px 8px;
  margin-right: 20px;
  margin-left: 20px;
}
</style>