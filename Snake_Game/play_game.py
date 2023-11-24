import turtle
import random
# Constants
WIGTH = 500
HEIGHT = 500
DELAY = 150

offsets = {
    "up":[0,20],
    "down":[0,-20],
    "right":[20,0],
    "left":[-20,0]
    }
# direction functions
def go_up():
    global snake_direction
    if snake_direction != "down":
        snake_direction = "up"
def go_down():
    global snake_direction
    if snake_direction != "up":
        snake_direction = "down"
def go_right():
    global snake_direction
    if snake_direction != "left":
        snake_direction = "right"
def go_left():
    global snake_direction
    if snake_direction != "right":
        snake_direction = "left"
        
# Calculate distance betweet the snake food and the snake head
def get_distance(food,snake):
    return ((food[0]-snake[0])**2+(food[1]-food[0])**2)**0.5
# random psotion
def get_random_position():
    return (random.randint(-290,290),random.randint(-290,290))

def food_collision():
    global food_position, score
    if get_distance(snake_list[-1], food_position) < 20:
        score += 1
        food_position = get_random_position()
        food.setposition(food_position)
        return True
    return False
    
def animate_snake():
    stamper.clearstamps()
    
    new_head = snake_list[-1].copy()
    new_head[0] += offsets[snake_direction][0]
    new_head[1] += offsets[snake_direction][1]
    
    # Check for collisions
    if new_head in snake_list or new_head[0]>290 or new_head[0]<-300\
        or new_head[1]>300 or new_head[1] <-300:
        reset()

    else:
        # Add new head to the snake body
        snake_list.append(new_head)
    
        if not food_collision():
            snake_list.pop(0)
        
        for position in snake_list:
            stamper.setposition(position[0],position[1])
            stamper.stamp()
        
        screen.update()
    # After `DELAY` milliseconds, call move_turtle again.
    screen.ontimer(animate_snake,DELAY)
    
def reset():
    global snake_direction,snake_list, food_position
    #  The snake list cooordinates at the start of the game
    
    food_position = get_random_position()
    snake_list = [[0,0],[20,0],[40,0],[60,0]]
    food.setposition(food_position)
    snake_direction = "up"
    
    # Set animation in motion
    animate_snake()
# Screen
screen = turtle.Screen()
# screen.setup(500,500)
screen.bgcolor("cyan")

# Turtle object to do my bidding
stamper = turtle.Turtle()
stamper.shape("circle")
stamper.color("black")
# stamper.shapesize()
stamper.penup()
screen.tracer(0)

# Set keyboard bindings
screen.listen()
screen.onkey(go_up, "Up")
screen.onkey(go_right, "Right")
screen.onkey(go_left, "Left")
screen.onkey(go_down, "Down")

#Food
food = turtle.Turtle()
food.color("green")
food.shape("circle")
food.penup()

#Draw border
my_pen = turtle.Turtle()
my_pen.color("black")
my_pen.penup()
my_pen.setposition(-300,-300)
my_pen.pendown()
my_pen.pensize(4)
for side in range(4):
    my_pen.forward(600)
    my_pen.left(90)
my_pen.hideturtle()



# Set animation in motion
reset()

turtle.done()




