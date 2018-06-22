<template>
<div class="grid">
  <div class="status">status: {{ life.state }}</div>
   <div class="controls">
     <button @click="life.start">Start</button>
     <button @click="life.stop">Stop</button>
     <button @click="life.reset">Reset</button>
   </div>
    <tbody>
      <tr class="row" v-for="(row, rInd) in grid" :key="`row-${rInd}`">
        <td
          class="cell"
          v-for="(cell, cInd) in row"
          :class="cell === 0 ? 'dead' : 'alive'"
          :key="`cell-${rInd}${cInd}`"
           @click="life.update(rInd, cInd)">
        </td>
      </tr>
    </tbody>
</div>
</template>

<script>
import Life from '../services/Life'
export default {
  name: 'grid',
  computed: {
    grid () {
      return this.$store.getters.getGrid
    }
  },
  data () {
    return {
      life: new Life(this, {x: 50, y: 50})
    }
  }
}
</script>

<style scoped lang="sass">
$cell-border: 1px solid grey
$font-size: 8px
$cell-size: 12px
$alive-color: #42b983
$dead-color: #272822
.grid
  border: $cell-border
  display: inline-block

.status
  display: flex
  justify-content: center

.controls
  display: flex
  justify-content: space-between
  background: transparent

  button
    width: 33%

.cell
  border: $cell-border
  font-size: $font-size
  width: $cell-size
  height: $cell-size
  cursor: pointer

.alive
  background: $alive-color

.dead
  background: $dead-color
</style>
