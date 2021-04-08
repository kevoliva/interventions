<template>
  <div class="container">
    <div class="border border-success rounded">

      <div class="text-center">
        <h2 class="text-success">{{ intervention.prenomClient }} {{ intervention.nomClient }}</h2>
        <h4 class="text-muted">{{ intervention.adresseClient }}</h4>
        <span class="text-muted">{{ format_date(intervention.dateIntervention) }}</span>
      </div>

      <div class="my-3 mx-3">
        <h5 class="text-muted">Marque de la chaudière : <span class="text-success">{{ intervention.marqueChaudiere }}</span></h5>
        <h5 class="text-muted">Modèle de la chaudière : <span class="text-success">{{ intervention.modeleChaudiere }}</span></h5>
        <h5 class="text-muted">Date de mise en service : <span class="text-success">{{ format_date(intervention.dateMiseEnService) }}</span></h5>
        <h5 class="text-muted">Numéro de série de la chaudière : <span class="text-success">{{ intervention.numeroSerie }}</span></h5>
        <h5 class="text-muted">Temps passé : <span class="text-success">{{ convertTime(intervention.tempsPasse) }}</span></h5>
        <h5 class="text-muted">Description :</h5>
        <p class="text-muted">{{ intervention.description }}</p>


      </div>
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
    ...mapState(['loading', 'intervention']),
  },
  mounted() {
    this.$store.dispatch('getIntervention', this.id);
  },
  unmounted() {
    this.$store.commit('intervention', {});
  },
  methods: {
    format_date(value) {
      if (value) {

        return moment(value).format("DD MM YYYY");
      }
    },
    convertTime(temps) {
      if(temps) {
        var heures = Math.floor(temps/60);
        var minutes = temps%60;
        if (minutes < 10) {
          return heures + "h0" + minutes;
        }
        else {
          return heures + "h" + minutes;
        }
      }
    }
  }
};
</script>

<style>
</style>
