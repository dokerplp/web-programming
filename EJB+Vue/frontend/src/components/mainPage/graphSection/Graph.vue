<template>
  <div id="left">
    <Canvas
        r='r'
        @sendData="handleRequest"
        ref="canvas"
    />
    <Form
        @find="findAll"
        @clear="clear"
        @sendData="handleRequest"
        @redraw="redraw"
    />
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

import Canvas from "@/components/mainPage/graphSection/canvasSection/Canvas";
import Form from "@/components/mainPage/graphSection/formSection/Form";

export default {
  name: "Graph",
  props: {
    login: {
      type: String,
      default() {
        return "Undefined"
      }
    },
    pass: {
      type: String,
      default() {
        return "Undefined"
      }
    },
    reg: {
      type: Boolean,
      default() {
        return false
      }
    }
  },
  data() {
    return {
      args: {
        r: '',
        error: ''
      }
    }
  },
  created() {
    this.findAll()
  },
  methods: {
    redraw(r) {
      this.$refs.canvas.draw(r)
    },
    handleRequest(data) {
      if (this.reg) {
        this.error = ""
        postData('/api/result', {
          login: this.login,
          password: this.pass,
          x: data.x.toString(),
          y: data.y.toString(),
          r: data.r.toString()
        })
            .then(response => response.json())
            .then(data => {
              console.log(data)
              this.$emit('table', data)
              this.$refs.canvas.dots(data)
            });
      }
    },
    findAll() {
      this.error = ""
      postData('/api/result/find', {
        login: this.login,
        password: this.pass,
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.$emit('table', data)
            this.$refs.canvas.dots(data)
          });
    },
    clear() {
      this.error = ""
      postData('/api/result/clear', {
        login: this.login,
        password: this.pass,
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.$emit('table', {data: [], status: data.status})
            this.$refs.canvas.clear_dots();
          });
    }
  },
  components: {Form, Canvas}
}
</script>

<style scoped>

</style>