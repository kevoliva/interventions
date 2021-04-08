<template>
  <div class="container">
    <form @submit.prevent="onSubmit()">
      <div class="mb-3">
        <label class="form-label">Prénom - Nom du technicien</label>
        <input v-model="name" type="name" class="form-control" id="exampleInputName">
        <label class="form-label">Email du technicien</label>
        <input v-model="email" type="email" class="form-control" id="exampleInputEmail">
        <label class="form-label">Mot de passe du technicien</label>
        <input v-model="password" type="password" class="form-control" id="exampleInputPassword">
      </div>
      <button type="submit" class="btn btn-success">Créer</button>
    </form>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      name: '',
      email: '',
      password: '',
    };
  },
  methods: {
    onSubmit() {
      this.$store.dispatch('postUser', { name: this.name, email: this.email, password: this.password })
      .then((user) => {
        this.$router.push({ name: 'Home'})
      });
    }
  }
};
</script>
