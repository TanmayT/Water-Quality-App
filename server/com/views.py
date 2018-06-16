
from django.http import HttpResponse

led = "0";
millis = "0";
sensor1_read = "7";

def index(request):
    return HttpResponse("<h1> Com  homepage </h1>")

def push(request):
    print (request)
    global led
    led = request.GET["led"]
    return HttpResponse(led + "/" + sensor1_read + "/" + millis);

def data(request):
    print (request)
    global millis
    millis = request.GET["millis"]
    global sensor1_read
    sensor1_read = request.GET["distance"]
    print (millis)
    print (sensor1_read)
    return HttpResponse("led="+led)
