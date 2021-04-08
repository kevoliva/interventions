import { createRouter, createWebHistory } from 'vue-router';
import Home from './views/Home.vue';
import User from './views/User.vue';
import AjouterUser from './views/AjouterUser.vue';
import Interventions from './views/Interventions.vue';
import Intervention from './views/Intervention.vue';

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/user/:id', name: 'User', component: User, props: true },
  { path: '/user/nouveau', name: 'AjouterUser', component: AjouterUser},
  { path: '/interventions', name: 'Interventions', component: Interventions},
  { path: '/intervention/:id', name: 'Intervention', component: Intervention, props: true}
];

const router = createRouter({
  routes,
  history: createWebHistory(),
  linkActiveClass: 'active',
});

export default router;
