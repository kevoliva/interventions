<template>
  <div class="container">
    <h1 class="text-center text-success">Techniciens</h1>
    <div class="row mt-5">

      <!-- Barre de recherche -->
      <div class="input-group rounded mb-4">
        <input type="search" class="form-control rounded" v-model="searchQuery" placeholder="Nom du technicien..." aria-label="Search"
        aria-describedby="search-addon" />
        <span class="input-group-text border-0" id="search-addon">
          <i class="fas fa-search"></i>
        </span>
      </div>

      <div class="mb-2 moitie animate__animated animate__lightSpeedInLeft">
        <label for="dateDebut">Date de début</label>
        <input type="date" class="form-control" v-model="startDate" id="dateDebut">
      </div>
      <div class="mb-4 moitie animate__animated animate__lightSpeedInRight">
        <label for="dateFin">Date de fin</label>
        <input type="date" class="form-control" v-model="endDate" id="dateFin">
      </div>

      <div class="text-center mb-2">
        <h5>Nombre de techniciens : {{ filteredDate.length }}</h5>
      </div>

      <table class="table table-striped">
        <thead class="table-success">
          <tr>
            <th scope="col" @click="sort('name')">Prénom & Nom <i class="fas fa-sort"></i></th>
            <th scope="col" @click="sort('email')">Email <i class="fas fa-sort"></i></th>
            <th scope="col" @click="sort('created_at')">Date d'inscription <i class="fas fa-sort"></i></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in filteredDate" :key="item.id">
            <td>{{ item.name }}</td>
            <td>{{ item.email }}</td>
            <td>{{ format_date(item.created_at) }}</td>
            <td><router-link :to="{ name: 'User', params: { id: item.id } }">
              <i class="far fa-eye"></i>
            </router-link></td>
          </tr>
        </tbody>
      </table>
      <div class="mt-3 text-center text-success">
        <button class="btn btn-success mx-2" @click="prevPage">Précédent</button>
        <button class="btn btn-success mx-2" @click="nextPage">Suivant</button>
        <br> <br>
        Tri sur la colonne : {{currentSort}}
        <br>
        Ordre : {{currentSortDir}}
        <br>
        Page : {{currentPage}}
      </div>
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
      currentSort:'name',
      currentSortDir:'asc',
      pageSize:10,
      currentPage:1,
      startDate: null,
      endDate: null,
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
    filteredDate (){
      var vm = this;
      var startDate = vm.startDate;
      var endDate = vm.endDate;
      return _.filter(vm.sortedUsers, function (data) {
        if ((_.isNull(startDate) && _.isNull(endDate))) {
          return true;
        } else {
          var date = data.created_at;
          return (date >= startDate && date <= endDate);
        }
      })
    },
    sortedUsers:function() {
      return this.filteredResources.sort((a,b) => {
        let modifier = 1;
        if(this.currentSortDir === 'desc') modifier = -1;
        if(a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
        if(a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
        return 0;
      }).filter((row, index) => {
        let start = (this.currentPage-1)*this.pageSize;
        let end = this.currentPage*this.pageSize;
        if(index >= start && index < end) return true;
      });
    },
    ...mapState(['loading', 'users']),
  },
  mounted() {
    this.$store.dispatch('getUsers');
  },
  methods: {
    nextPage:function() {
      if((this.currentPage*this.pageSize) < this.users.length) this.currentPage++;
    },
    prevPage:function() {
      if(this.currentPage > 1) this.currentPage--;
    },
    sort:function(s) {
      //if s == current sort, reverse
      if(s === this.currentSort) {
        this.currentSortDir = this.currentSortDir==='asc'?'desc':'asc';
      }
      this.currentSort = s;
    },
    format_date(value) {
      if (value) {
        return moment(value).format("DD/MM/YYYY");
      }
    }
  }
};
</script>
