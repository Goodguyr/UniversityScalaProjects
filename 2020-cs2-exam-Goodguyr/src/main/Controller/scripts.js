const socket = io.connect("http://localhost:8080", {
'path': '/api/cs2_remote?name=' += socket.id;
transports: ['websocket']
});

const currentChannel = document.getElementsByClassName("channel")
const currentSound = document.getElementById("sound")
const enabled = document.getElementsByClassName("enabled")

function sendData(data){
    var response = {
        "Method": "SendRemoteKey",
        "Cmd": "Click",
        "Data": data
        }
        
    socket.emit("buttonClick", JSON.stringify(response))
}

socket.on("responseFromServer")