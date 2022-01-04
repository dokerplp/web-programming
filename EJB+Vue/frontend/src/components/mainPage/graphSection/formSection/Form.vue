<template>
  <div id="form2">
    <div>
      Form
    </div>
    <div>
      <table id="values">
        <tr>
          <td>
            X
          </td>
          <td>
            <label><input v-model="x" type="radio" name="x" value="-5"/>-5</label>
            <label><input v-model="x" type="radio" name="x" value="-4"/>-4</label>
            <label><input v-model="x" type="radio" name="x" value="-3"/>-3</label>
            <label><input v-model="x" type="radio" name="x" value="-2"/>-2</label>
            <label><input v-model="x" type="radio" name="x" value="-1"/>-1</label>
            <label><input v-model="x" type="radio" name="x" value="0"/>0</label>
            <label><input v-model="x" type="radio" name="x" value="1"/>1</label>
            <label><input v-model="x" type="radio" name="x" value="2"/>2</label>
            <label><input v-model="x" type="radio" name="x" value="3"/>3</label>
          </td>
        </tr>
        <tr>
          <td>
            Y
          </td>
          <td>
            <input v-model="y" type="text" name="y" placeholder="from -5 to 5" v-on:change="check_y"/>
          </td>
        </tr>
        <tr>
          <td>
            R
          </td>
          <td>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="-5"/>-5</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="-4"/>-4</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="-3"/>-3</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="-2"/>-2</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="-1"/>-1</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="0"/>0</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="1"/>1</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="2"/>2</label>
            <label><input v-model="r" v-on:change="check_r" type="radio" name="r" value="3"/>3</label>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <p v-if="!y_valid" class="error">{{ y_err }}</p>
            <p v-if="!r_valid" class="error">{{ r_err }}</p>
          </td>
        </tr>
        <tr>
          <td colSpan="2" id="buttons">
            <button class="button" v-on:click="sendData">Submit</button>
            <button class="button" v-on:click="clear">Clear</button>
            <router-link to="/" tag="button" class="button">Previous</router-link>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Form",
  data() {
    return {
      x: '0',
      y: '',
      r: '0',
      y_valid: false,
      r_valid: false,
      y_err: 'Y must be in section [-5; 5]',
      r_err: 'R value must be greater than 0'
    }
  },
  methods: {
    check_y() {
      this.y_valid = ((this.y.match(/^-?[0-4](\.\d+)?$/) || this.y.match(/^-?5$/)) && !this.y.match(/^-0(\.0+)?$/));
    },
    check_r() {
      this.r_valid = (this.r > 0)
      this.redraw()
    },
    sendData() {
      if (this.y_valid && this.r_valid) {
        this.$emit('sendData', {
          x: this.x,
          y: this.y,
          r: this.r
        })
      }
    },
    find() {
      this.$emit('find');
    },
    clear() {
      this.$emit('clear');
    },
    redraw() {
      if (this.r_valid) this.$emit('redraw', this.r)
      else this.$emit('redraw', 0)
    }
  }
}
</script>

<style scoped>
#form2 div:first-child {
  background: #ad0aad;
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin-right: 20px;
  margin-top: 20px;
  margin-left: 20px;
}

#form2 div:last-child {
  margin-right: 20px;
  margin-left: 20px;
  margin-bottom: 20px;
}

#values {
  background: white;
  border-spacing: 20px 20px;
  width: 100%;
  border-radius: 0 0 8px 8px;
}

#values tr td:first-child {
  width: 20%;
  background: #671e67;
  font-size: 25px;
  border-radius: 8px 8px 8px 8px;
}

#values tr td:last-child {
  background: white;
}

.button {
  background-color: #671e67;
  color: white;
  border-radius: 8px 8px 8px 8px;
  border-width: 1px;
  border-color: #671e67;
  font-size: 20px;
}

#values input[type="text"] {
  background: white;
  color: black;
  width: 100%;
  border-radius: 8px 8px 8px 8px;
  border-width: 2px;
  border-color: black;
  font-size: 15px;
}

#buttons button {
  margin-right: 20px;
}

input[type=radio] {
  display: block;
  margin: 7px;
}

label {
  background-color: white;
  color: black;
  display: inline-block;
}

.error {
  font-size: 20px;
  color: #E51C22;
  background-color: white;
  text-align: center;
  margin: auto;
}
</style>