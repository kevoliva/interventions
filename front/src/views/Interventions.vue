<template>
  <div class="container">
    <h1 class="text-center text-success">Interventions</h1>
    <div class="row mt-5">
      <!-- Barre de recherche -->
      <div class="input-group rounded mb-3">
        <input type="search" class="form-control rounded" v-model="searchQuery" placeholder="Nom du client..." aria-label="Search"
        aria-describedby="search-addon" />
        <span class="input-group-text border-0" id="search-addon">
          <i class="fas fa-search"></i>
        </span>
      </div>
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
          <tr v-for="item in filteredResources" :key="item.id">
            <td>{{ item.nomClient }}</td>
            <td>{{ item.prenomClient }}</td>
            <td>{{ item.adresseClient }}</td>
            <td>{{ format_date(item.dateIntervention) }}</td>
            <td><router-link :to="{ name: 'Intervention', params: { id: item.id } }">
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
  data() {
    return {
      searchQuery: '',
    }
  },
  computed: {
    filteredResources (){
      if(this.searchQuery){
        return this.interventions.filter((item)=>{
          return item.nomClient.includes(this.searchQuery);
        })
      }else{
        return this.interventions;
      }
    },
    ...mapState(['loading', 'interventions']),
  },
  mounted() {
    this.$store.dispatch('getInterventions');
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
