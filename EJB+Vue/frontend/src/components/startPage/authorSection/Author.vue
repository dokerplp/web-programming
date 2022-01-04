<template>
  <div id="author">
    <div id="student">
      <div>Student</div>
      <div>Valerii Butorin</div>
    </div>
    <div id="group">
      <div>Group</div>
      <div>P3231</div>
    </div>
    <div id="option">
      <div>Option</div>
      <div>1343</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Author"
}
</script>

<style scoped>

#student {
  float: left;
  width: 40%;
}

#group {
  float: left;
  width: 30%;
}

#option {
  float: left;
  width: 30%;
}

#author div div:first-child {
  background: #ad0aad;
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin-right: 20px;
  margin-top: 20px;
}

#author div:first-child div {
  margin-left: 20px;
}

#author div div:last-child {
  background: #d760d7;
  font-size: 25px;
  border-radius: 0 0 8px 8px;
  margin-right: 20px;
}
</style>