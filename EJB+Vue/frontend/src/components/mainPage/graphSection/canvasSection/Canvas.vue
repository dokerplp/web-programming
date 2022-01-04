<template>
  <div id="graph">
    <div>
      Graph
    </div>
    <div>
      <canvas id="canvas" width="1000" height="1000"  v-on:click="sendData" @mousemove="coordinates"/>
    </div>
  </div>
</template>

<script>


export default {
  name: "Canvas",
  mounted() {
    var c = document.getElementById("canvas");
    this.canvas = c.getContext('2d');

    this.draw();
  },
  data() {
    return {
      x: '',
      y: '',
      r: 0,
      canvas: null,
      results: []
    }
  },
  methods: {
    sendData(){
      if (this.r > 0) {
        this.$emit('sendData', {
          x: this.x,
          y: this.y,
          r: this.r
        })
      }
    },
    coordinates(e){
      var c = document.getElementById("canvas");

      let clientSize = c.clientWidth;
      let canvasSize = c.width;

      let hor = e.offsetX / clientSize * canvasSize;
      let ver = e.offsetY / clientSize * canvasSize;

      this.x = Math.round((hor - 500) / 90 * 100) / 100
      this.y = Math.round((500 - ver) / 90 * 100) / 100

    },
    dots(data) {
      this.results = data.data
      this.draw(this.r)
    },
    dot(ctx, left, top, res) {
      if (res === 'true') ctx.fillStyle = 'green'
      else ctx.fillStyle = 'red'
      ctx.beginPath();
      left = parseFloat(left.replace(",", ".")) * 1000
      top = parseFloat(top.replace(",", ".")) * 1000
      ctx.arc(left, top, 10, 0, Math.PI * 2, false);
      ctx.fill()
    },
    draw_dots(){
      this.results.forEach(el => {
        if (el.r === this.r){
          this.dot(this.canvas, el.left, el.top, el.res)
        }
      })
    },
    clear_dots() {
      this.results = []
      this.draw(this.r)
    },
    draw(radius) {

      this.r = radius

      radius = 90 * radius

      let ctx = this.canvas;
      ctx.clearRect(0, 0, 1000, 1000);
      ctx.lineWidth = 5


      this.canvas_rect(ctx, 500, 500, radius, radius / 2, '#35FFD7')
      this.canvas_arc(ctx, 500, 500, radius, -Math.PI / 2, 0, '#35FFD7')
      this.canvas_triangle(ctx, 500, 500, 500 - radius, 500, 500, 500 + radius / 2, '#35FFD7');

      this.canvas_arrow(ctx, 500, 1000, 500, 0);
      this.canvas_arrow(ctx, 0, 500, 1000, 500);
      this.draw_points(ctx);

      this.draw_dots()
    },
    draw_points(ctx){

      ctx.fillStyle = 'black'

      this.draw_point(ctx, 500, 500, '0')

      this.draw_point(ctx, 500, 50, '5')
      this.draw_point(ctx, 500, 140, '4')
      this.draw_point(ctx, 500, 230, '3')
      this.draw_point(ctx, 500, 320, '2')
      this.draw_point(ctx, 500, 410, '1')
      this.draw_point(ctx, 500, 590, '-1')
      this.draw_point(ctx, 500, 680, '-2')
      this.draw_point(ctx, 500, 770, '-3')
      this.draw_point(ctx, 500, 860, '-4')
      this.draw_point(ctx, 500, 950, '-5')


      this.draw_point(ctx, 50, 500, '-5')
      this.draw_point(ctx, 140, 500, '-4')
      this.draw_point(ctx, 230, 500, '-3')
      this.draw_point(ctx, 320, 500, '-2')
      this.draw_point(ctx, 410, 500, '-1')
      this.draw_point(ctx, 590, 500, '1')
      this.draw_point(ctx, 680, 500, '2')
      this.draw_point(ctx, 770, 500, '3')
      this.draw_point(ctx, 860, 500, '4')
      this.draw_point(ctx, 950, 500, '5')
    },
    draw_point(ctx, fromx, fromy, text) {
      ctx.beginPath();
      ctx.arc(fromx, fromy, 10, 0, Math.PI * 2, false);
      ctx.fill()
      ctx.stroke();
      ctx.font = "48px als schlange sans";
      ctx.fillText(text, fromx + 10, fromy + 50)
    },
    canvas_arrow(context, fromx, fromy, tox, toy) {
      context.beginPath();
      var headlen = 30; // length of head in pixels
      var dx = tox - fromx;
      var dy = toy - fromy;
      var angle = Math.atan2(dy, dx);
      context.moveTo(fromx, fromy);
      context.lineTo(tox, toy);
      context.lineTo(tox - headlen * Math.cos(angle - Math.PI / 6), toy - headlen * Math.sin(angle - Math.PI / 6));
      context.moveTo(tox, toy);
      context.lineTo(tox - headlen * Math.cos(angle + Math.PI / 6), toy - headlen * Math.sin(angle + Math.PI / 6));
      context.stroke();
    },
    canvas_rect(ctx, fromx, fromy, width, height, color) {
      ctx.beginPath();
      ctx.fillStyle = color
      ctx.rect(fromx, fromy, width, height);
      ctx.fill();
      ctx.stroke();
    },
    canvas_arc(ctx, fromx, fromy, rad, ang_start, ang_end, color){
      ctx.fillStyle = color
      ctx.beginPath();
      ctx.arc(fromx, fromy, rad, ang_start, ang_end, false);
      ctx.moveTo(500 + rad, 500);
      ctx.lineTo(fromx, fromy);
      ctx.lineTo(fromx, 500 - rad);
      ctx.fill()
      ctx.stroke();
    },
    canvas_triangle(ctx, x1, y1, x2, y2, x3, y3, color){
      ctx.fillStyle = color
      ctx.beginPath();
      ctx.moveTo(x1,y1);
      ctx.lineTo(x2,y2);
      ctx.lineTo(x3,y3);
      ctx.lineTo(x1,y1);
      ctx.fill();
      ctx.stroke();
    },
  },
}
</script>

<style scoped>
#graph div:first-child {
  background: #ad0aad;
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin-right: 20px;
  margin-top: 20px;
  margin-left: 20px;
}

#graph div:last-child {
  background: white;
  border-radius: 0 0 8px 8px;
  margin-right: 20px;
  margin-left: 20px;
}

#canvas {
  width: 100%;
  background-color: white;
  border-radius: 0 0 8px 8px;
}
</style>