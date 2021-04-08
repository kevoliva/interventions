import { createStore } from 'vuex';
import api from './api.js';

const store = createStore({
  // l'état contient les données
  state: {
    loading: false,
    users: [],
    user: {},
    interventions: [],
    intervention: {}
  },
  // les mutations permettent de modifier l'état de façon synchrone
  mutations: {
    loading(state, loading) {
      state.loading = loading;
    },
    users(state, users) {
      state.users = users;
    },
    user(state, user) {
      state.user = user;
    },
    interventions(state, interventions) {
      state.interventions = interventions;
    },
    intervention(state, intervention) {
      state.intervention = intervention;
    }
  },
  // les actions permettent de modifier l'état de façon asynchrone
  actions: {
    getUsers({ commit }) {
      commit('loading', true);
      api.get('user')
      .then(response => {
        commit('users', response.data.data);
      })
      .finally(() => {
        commit('loading', false);
      });
    },
    getUser({ commit }, id) {
      commit('loading', true);
      api.get('user/'+id, { params: { expand: 'interventions' } })
      .then(response => {
        commit('user', response.data.data);
      })
      .finally(() => {
        commit('loading', false);
      });
    },
    postUser({ commit }, user) {
      commit('loading', true);
      return new Promise((resolve, reject) => {
        api.post('user', user).then(response => {
          resolve(response.data.data);
        }).catch(error => {
          reject(error);
        }).finally(() => {
          commit('loading', false);
        });
      });
    },
    getInterventions({ commit }) {
      commit('loading', true);
      api.get('intervention')
      .then(response => {
        commit('interventions', response.data.data);
      })
      .finally(() => {
        commit('loading', false);
      });
    },
    getIntervention({ commit }, id) {
      commit('loading', true);
      api.get('intervention/'+id, { params: { expand: 'user' } })
      .then(response => {
        commit('intervention', response.data.data);
      })
      .finally(() => {
        commit('loading', false);
      });
    },
    postIntervention({ commit }, intervention) {
      commit('loading', true);
      return new Promise((resolve, reject) => {
        api.post('intervention', intervention).then(response => {
          resolve(response.data.data);
        }).catch(error => {
          reject(error);
        }).finally(() => {
          commit('loading', false);
        });
      });
    },
  }
});

export default store;
