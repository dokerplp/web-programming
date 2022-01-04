<template>
  <div id="right">
    <div id="auth">
      <div>
        Authentication
      </div>
      <div>

        <table id="form">
          <tr>
            <td class="login">Login</td>
            <td class="login">
              <input type="text" v-model="login"/>
            </td>
          </tr>
          <tr>
            <td id="label">Password</td>
            <td id="pass">
              <input type="password" v-model="pass"/>
            </td>
          </tr>
          <tr>
            <td colSpan="2">
              <button class="button" v-on:click="sign_in">Log in</button>
              <p id="error">{{ error }}</p>
            </td>
          </tr>
        </table>
      </div>
      <div>
        Not registered yet?
        <button v-on:click="sign_up" class="button" style="width: 90px">Sign up</button>
      </div>
    </div>
  </div>
</template>

<script>

async function postData(url, data) {
  return await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    body: JSON.stringify(data)
  })
}

export default {
  name: "Login",
  data: function () {
    return {
      login: "",
      pass: "",
      error: ""
    }
  },
  methods: {
    sign_in() {
      this.error = ""
      postData('/api/auth/sign-in', {
        login: this.login,
        password: this.pass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.error = data.data
            if (data.status.toString() === "true") {
              this.$router.push({name: 'main', params: {login: this.login, pass: this.pass, registered: 'true'}})
            }
          });
    },
    sign_up() {
      this.error = ""
      postData('/api/auth/sign-up', {
        login: this.login,
        password: this.pass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.error = data.data
            if (data.status.toString()) {
              this.$router.push({name: 'main', params: {login: this.login, pass: this.pass, registered: 'true'}})
            }
          });
    }
  }
}
</script>

<style scoped>
#auth div:first-child {
  background: #ad0aad;
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin-top: 20px;
  margin-right: 20px;
}

#auth div:nth-child(2) {
  margin-right: 20px;
}

#auth div:last-child {
  font-size: 20px;
  background: #985198;
  margin-right: 20px;
  border-radius: 0 0 8px 8px;
}

#auth div:last-child a {
  background: #985198;
  font-weight: bold;
}

#form {
  background: white;
  border-spacing: 20px 20px;
  width: 100%;
  /*border-radius: 0 0 8px 8px;*/
}

#form tr td:first-child {
  background: #985198;
  font-size: 20px;
  border-radius: 8px 8px 8px 8px;
}

#form tr td:last-child {
  background: white;
}

.button {
  background-color: #671e67;
  color: white;
  border-radius: 8px 8px 8px 8px;
  border-width: 1px;
  border-color: #671e67;
  font-size: 20px;
  font-weight: bold;
  width: 30%;
}

input[type="text"], input[type="password"] {
  background: white;
  color: black;
  width: 90%;
  border-radius: 8px 8px 8px 8px;
  border-width: 2px;
  border-color: black;
  font-size: 15px;
}

#error {
  color: #E51C22;
  background-color: white;
}
</style>