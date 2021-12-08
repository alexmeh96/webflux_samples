// const eventSource = new EventSource("http://localhost:8081/stream-flux");
const eventSource = new EventSource("http://localhost:8081/stream-sse");

eventSource.onopen = (event) => {
    console.log("connection opened")
}

eventSource.onmessage = (event) => {
    console.log("result", event.data);
}

eventSource.onerror = (event) => {
    console.log("onError")
    console.log(event.target.readyState)
    if (event.target.readyState === EventSource.CLOSED) {
        console.log('eventsource closed (' + event.target.readyState + ')')
    }
    eventSource.close();
}

eventSource.addEventListener("periodic-event", (event) => {
    const result = JSON.parse(event.data);
    console.log(`received: ${result.message} - ${result.time}`);
});