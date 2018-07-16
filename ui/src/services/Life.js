import Ws from './Ws'

class Life extends Ws {
  constructor (context, dimensions) {
    super()
    this.context = context
    this.$store = this.context.$store
    this.dimensions = dimensions

    this.state = ''

    this._getGrid = this._getGrid.bind(this)
    this.start = this.start.bind(this)
    this.continue = this.continue.bind(this)
    this.stop = this.stop.bind(this)
    this._controlFlow = this._controlFlow.bind(this)
    this.reset = this.reset.bind(this)

    this.speed = 500

    this._openSocket(this._getGrid)

    this._onMessage(res => {
      this.state = res.message
      this.$store.dispatch('updateGrid', res.grid)
    })
  }

  continue () {
    this._send({action: 'continue'})
  }

  start () {
    this._controlFlow()
    this._send({action: 'start'})
  }

  stop () {
    this._send({action: 'stop'})
  }

  reset () {
    this._send({action: 'reset'})
  }

  adjustSpeed (direction) {
    if (direction === 'slower' || this.speed > 0) {
      this.speed += direction === 'faster' ? -100 : 100
    }
  }

  update (x, y) {
    this._send({action: 'update', update: {x: x, y: y}})
  }

  _controlFlow () {
    if (this.state === 'stopping' || this.state === 'reset') {} else {
      setTimeout(() => {
        this.continue()
        this._controlFlow()
      }, this.speed)
    }
  }

  _getGrid () {
    this._send({action: 'get-grid', dimensions: this.dimensions})
  }
}

export default Life
