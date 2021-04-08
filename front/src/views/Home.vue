<template>
  <div class="container">
    <h1 class="text-center text-success">Techniciens</h1>
    <div class="row mt-5">
      <!-- Barre de recherche -->
      <div class="input-group rounded mb-3">
        <input type="search" class="form-control rounded" v-model="searchQuery" placeholder="Nom du technicien..." aria-label="Search"
        aria-describedby="search-addon" />
        <span class="input-group-text border-0" id="search-addon">
          <i class="fas fa-search"></i>
        </span>
      </div>
      <table class="table table-striped">
        <thead class="table-success">
          <tr>
            <th scope="col">Prénom & Nom</th>
            <th scope="col">Email</th>
            <th scope="col">Date d'inscription</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in filteredResources" :key="item.id">
            <td>{{ item.name }}</td>
            <td>{{ item.email }}</td>
            <td>{{ format_date(item.created_at) }}</td>
            <td><router-link :to="{ name: 'User', params: { id: item.id } }">
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
        return this.users.filter((item)=>{
          return item.name.includes(this.searchQuery);
        })
      }else{
        return this.users;
      }
    },
    ...mapState(['loading', 'users']),
  },
  mounted() {
    this.$store.dispatch('getUsers');
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
