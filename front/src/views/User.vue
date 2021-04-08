<template>
  <div class="container">
    <h1 class="text-center text-success">Interventions réalisées par {{ user.name }}</h1>
    <div class="row mt-5">
      <table class="table table-striped">
        <thead class="table-success">
          <tr>
            <th scope="col">Nom du client</th>
            <th scope="col">Prénom du client</th>
            <th scope="col">Adresse du client</th>
            <th scope="col">Date de l'intervention</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="intervention in user.interventions" :key="intervention.id">
            <td>{{ intervention.nomClient }}</td>
            <td>{{ intervention.prenomClient }}</td>
            <td>{{ intervention.adresseClient }}</td>
            <td>{{ format_date(intervention.dateIntervention) }}</td>
            <td><router-link :to="{ name: 'Intervention', params: { id: intervention.id } }">
              <i class="far fa-eye"></i>
            </router-link></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="loading" class="spinner-border text-success" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import moment from "moment";

export default {
  props: ['id'],
  data() {
    return {
      vbWidth: 0,
      vbHeight: 0,
    };
  },
  computed: {
    ...mapState(['loading', 'user']),
  },
  mounted() {
    this.$store.dispatch('getUser', this.id);
  },
  unmounted() {
    this.$store.commit('user', {});
  },
  methods: {
    format_date(value) {
      if (value) {
        return moment(value).format("DD/MM/YYYY");
      }
    }
  }
};
</script>

<style>
</style>
