<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-main">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-between" id="navbar-main">
          <div class="navbar-nav">
            <router-link to="/" class="navbar-brand">Accueil</router-link>
          </div>
          <ul class="navbar-nav mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link :to="{ name:'Interventions'}" class="nav-link">Toutes les interventions</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name:'AjouterUser'}" class="nav-link">Ajouter un technicien</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container mt-4">
      <router-view v-slot="{ Component }">
        <transition
        mode="out-in"
        enter-active-class="animate__flipInY"
        leave-active-class="animate__flipOutX">
        <component :is="Component" />
      </transition>
    </router-view>
  </div>
</div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  computed: mapState(['doodle'])
};
</script>
