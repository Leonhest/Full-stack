import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import LogIn from '../views/LogIn.vue'
import NewUser from '../views/NewUser.vue'
import store from '../store';

const routes = [
    {
        path: '/login',
        name: 'LogIn',
        component: LogIn,
    },
    {
        path: "/",
        redirect: "/login"
    },
    {
        path: '/register',
        name: 'Register',
        component: NewUser,
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        meta:{
            requiresAuth: true
        }
    },
    {
        path: '/contact',
        name: 'Contact',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/*webpackChunkName: "contact" */ '../views/Contact.vue')
    }
]
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
      // this route requires auth, check if logged in
      // if not, redirect to login page.
      if (!store.state.IsLoggedIn === true) {
        console.log(store.state.IsLoggedIn)
        next({ name: 'LogIn' })
      } else {
        next() // go to wherever I'm going
      }
    } else {
      next() // does not require auth, make sure to always call next()!
    }
  })