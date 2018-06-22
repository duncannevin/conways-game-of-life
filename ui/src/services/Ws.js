class Ws {
  constructor () {
    this._SOCKET_LOCATION = 'ws://localhost:9000/v1/life'
    this._ws = null
  }

  _onMessage (cb) {
    this._ws.onmessage = (msg) => {
      cb(JSON.parse(msg.data))
    }
  }

  _send (message) {
    this._ws.send(JSON.stringify(message))
  }

  _openSocket (openCb) {
    if (this._ws === null || this._ws.readyState !== 1) {
      this._ws = new WebSocket(this._SOCKET_LOCATION)
      this._ws.onopen = openCb
      this._ws.onerror = (err) => {
        console.error('COMPILER WS FAILED', err)
      }
      this._ws.onclose = () => {
        this._openSocket()
      }
    }
  }
}

export default Ws
