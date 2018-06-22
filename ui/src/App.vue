<template>
  <div id="app">
    <grid></grid>
  </div>
</template>

<script>
import Grid from './components/Grid'

export default {
  name: 'App',
  components: {
    Grid
  }
}
</script>

<style lang="sass">
$background-color: #42b983
body
  background-color: $background-color
#app
  font-family: 'Avenir', Helvetica, Arial, sans-serif
  -webkit-font-smoothing: antialiased
  -moz-osx-font-smoothing: grayscale
  display: flex
  justify-content: center
  background: $background-color
  color: #2c3e50
</style>
