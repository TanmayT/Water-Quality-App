
from django.http import HttpResponse
import random
led = "0";
millis = "0";
sensor1_read = str(random.randint(0,3));

def index(request):
    return HttpResponse("<h1> Com  homepage </h1>")

def push(request):
    print (request)
    global led
    led = request.GET["led"]
    return HttpResponse(led + "/" + str(random.randint(0,3)) + "/" + millis);

def data(request):
    print (request)
    global millis
    millis = request.GET["millis"]
    global sensor1_read
    sensor1_read = request.GET["distance"]
    print (millis)
    print (sensor1_read)
    return HttpResponse("led="+led)
