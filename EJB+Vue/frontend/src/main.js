import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import Main from "@/pages/Main";
import Start from "@/pages/Start";

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {path: '/', component: Start},
    {path: '/', name: 'main', component: Main },
  ],
  mode: "history"
})
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')