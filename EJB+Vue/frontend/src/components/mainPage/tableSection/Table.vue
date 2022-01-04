<template>
  <div id="right">
    <div id="table">
      <div>
        Table
      </div>
      <div>
        <table id="data">
          <thead>
          <tr>
            <td id="x_col">X</td>
            <td id="y_col">Y</td>
            <td id="r_col">R</td>
            <td id="cur_col">Current time</td>
            <td id="ex_col">Execution time</td>
            <td id="res_col">Hit result</td>
          </tr>
          </thead>
          <tbody v-if="valid" id="rows">
          <tr v-for="result in results" :key="result.id">
              <td>
                {{result.x}}
              </td>
              <td>
                {{result.y}}
              </td>
              <td>
                {{result.r}}
              </td>
              <td>
                {{result.curTime}}
              </td>
              <td>
                {{result.exTime}} ms
              </td>
              <td>
                {{result.res}}
              </td>
          </tr>
          </tbody>
          <tbody v-if="!valid">
          <tr>
            <td colspan="6" style="color: white; background-color: #E51C22">
              {{error}}
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Table",
  data() {
    return {
      results: [],
      valid: false,
      error: 'Nothing found'
    }
  },
  methods: {
    setUp(data, valid, error) {
      this.results = data
      this.valid = valid === 'true'
      this.error = error
    }
  }
}
</script>

<style scoped>
#table div:first-child {
  background: #ad0aad;
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin-right: 20px;
  margin-top: 20px;
}

#table div:last-child {
  margin-right: 20px;
}

#data {
  background: white;
  border-spacing: 20px 20px;
  width: 100%;
  border-radius: 0 0 8px 8px;
}

#data thead * {
  background: #671e67;
  font-size: 25px;
  border-radius: 8px 8px 8px 8px;
}

#data tbody tr td {
  background: #985198;
  font-size: 20px;
  border-radius: 8px 8px 8px 8px;
}

#data tbody tr td.true {
  background: #87E549F9;
}

#data tbody tr td.false {
  background: #E51C22;
}

.img_true {
  background-color: #87E549F9;
  width: 20%;
  alignment: center;
}

.img_false {
  background-color: #E51C22;
  width: 20%;
  alignment: center;
}


#x_col {
  width: 10%;
}

#y_col {
  width: 10%;
}

#r_col {
  width: 10%;
}

#cur_col {
  width: 25%;
}

#ex_col {
  width: 25%;
}

#res_col {
  width: 20%;
}

#submit {
  margin-right: 20px;
}

#reset {
  margin-right: 20px;
}

#data tbody tr td.err_msg {
  background: #E51C22;
  font-size: 25px;
  border-radius: 8px 8px 8px 8px;
  color: white;
}
</style>