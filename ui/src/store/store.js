import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    grid: []
  },
  mutations: {
    UPDATE_GRID (state, grid) {
      state.grid = grid
    },
    CHANGE_CELL (state, {x, y}) {
      const toggle = state.grid[x][y] === 0 ? 1 : 0
      state.grid[x].splice(y, 1, toggle)
    }
  },
  actions: {
    updateGrid ({commit}, grid) {
      commit('UPDATE_GRID', grid)
    },
    changeCell ({commit}, {x, y}) {
      commit('CHANGE_CELL', {x, y})
    }
  },
  getters: {
    getGrid: state => state.grid
  }
})
