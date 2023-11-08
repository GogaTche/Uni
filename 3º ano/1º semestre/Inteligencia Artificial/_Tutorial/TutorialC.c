#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BOARD_SIZE 10
#define List Queue

typedef struct State{
    struct State* father;
    char *board;
    int weight;
}State;

State *state(State *father, char *board){
    State *result = (State *) malloc(sizeof(State));
    result->father = father;
    result->board = strdup(board);
    if(father != NULL) result->weight = father->weight + 1;
    else result->weight = 0;
    return result;
}

typedef struct Node{
    struct Node *next;
    State *value;
}Node;


Node *node(Node *next, State *value){
    Node *result = (Node *) malloc(sizeof(Node));
    result->next = next;
    result->value = value;
    return result; 
}

typedef struct Queue{
    Node *first;
    Node *last;
    int size;
}Queue;

Queue *queue(){
    Queue *result = (Queue*) malloc(sizeof(Queue));
    result->first = NULL;
    result->last = NULL;
    result->size = 0;
    return result; 
}

void free_node(Node *node){
    free(node->value);
    free(node);
}

void free_queue(Queue *queue){
    Node *node_temp;
    while(queue->first != NULL){
        node_temp = queue->first;
        queue->first = queue->first->next;
        free_node(node_temp);
    }
    free(queue);
}

void free_state(State *state){
    free(state->board);
    free(state);
}

void free_all_state(State *state){
    State *state_temp;
    while(state_temp != NULL){
        state_temp = state;
        state = state->father;
        free_state(state_temp);
    }
}

int get_queue_size(Queue *queue){
    return queue->size;
}

int is_queue_empty(Queue *queue){
    return queue->first == NULL ? 1 : 0;
}

void enqueue(Queue *queue, State *value){
    Node *node_temp = node(NULL, state(value->father, strdup(value->board)));
    if(is_queue_empty(queue))
        queue->first = node_temp;
    else
        queue->last->next = node_temp;
    queue->last = node_temp; 
    queue->size++;
}

void enqueue_all(Queue *queue, Queue *state_list){
    Node *node_temp = state_list->first;
    while(node_temp != NULL){
        enqueue(queue, node_temp->value);
        node_temp = node_temp->next;
    }
}

State *dequeue(Queue *queue){
    State *result = state(queue->first->value->father, queue->first->value->board);
    Node *node_temp = queue->first;
    queue->first = queue->first->next;
    free_node(node_temp);
    queue->size--;
    return result;
}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------


int get_blank_position(char *board){
    int result = -1;
    for(int i = 0; board[i] != '\0'; i++)
        if(board[i] == '0')
            result = i;
    
    return result;
}

char *swap(char *board, int x, int y){
    char *result = strdup(board);
    char c = result[x];
    result[x] = result[y];
    result[y] = c;
    return result;
}

List *get_succssessors(State *state_start){
    List *result = queue();
    int blank_position = get_blank_position(strdup(state_start->board));
     
    if(blank_position - 3 >= 0)
        enqueue(result, state(state_start, swap(state_start->board, blank_position, blank_position - 3)));
    

    if(blank_position + 3 <= BOARD_SIZE-2) 
        enqueue(result, state(state_start, swap(state_start->board, blank_position, blank_position + 3)));
    
    if((blank_position+1) % 3 == 2 || (blank_position) % 3 == 2)
        enqueue(result, state(state_start, swap(state_start->board, blank_position, blank_position - 1)));
    

    if((((blank_position + 1) % 3 == 2) || (blank_position + 2) % 3 == 2))
        enqueue(result, state(state_start, swap(state_start->board, blank_position, blank_position + 1)));

    return result;
}

int is_closed(State *current_state, List *closed){
    int result = 0;
    Node *closed_node = closed->first;
    while(closed_node != NULL){
        if(strcmp(closed_node->value->board, current_state->board) == 0){
            result = 1;
            break;
        }
        closed_node = closed_node->next;
    }
    return result;
}

List *get_not_closed_succssessors(State *state_start, List *closed){
    
    List *result = queue();
    List *succssessors = get_succssessors(state_start);
    while(!is_queue_empty(succssessors)){
        State *succssessor_state = dequeue(succssessors);
        if(!is_closed(succssessor_state, closed)){
            enqueue(result, succssessor_state);
        }
    }
    return result;
}

State *solve_puzzle(State *start, State *goal, Queue *open, List *closed){
    State *result;
    enqueue(open, start);
    while(!is_queue_empty(open)){
        State *possible_solution = dequeue(open);
        if(strcmp(possible_solution->board, goal->board) == 0){
            result = state(possible_solution->father, possible_solution->board);
            break;
        }
        enqueue_all(open, get_not_closed_succssessors(possible_solution, closed));
        enqueue(closed, possible_solution);
    }
    return result;
}

int get_array_of_state(char **array_of_state, State *state){
    State *state_temp = state;
    int result = state->weight + 1;
    int n = state->weight + 1;
    while(state_temp != NULL){
        array_of_state[n - 1] = strdup(state_temp->board);
        n--;
        state_temp = state_temp->father;
    }
    return result;
}

void print_board(char *board){ 
    for(int i = 1; i < BOARD_SIZE && board[i - 1] != '\0'; i++){
        if(board[i - 1] == '0')
            printf("%c ", ' ');
        else 
            printf("%c ", board[i - 1]);
        if(i % 3 == 0)
            printf("\n");
    }
}

void print_state(State *state){
    char **array_of_state = (char**) malloc(BOARD_SIZE * 1000 * sizeof(char));
    int n = get_array_of_state(array_of_state, state);
    for(int i = 0; i < n; i++){
        print_board(strdup(array_of_state[i]));
        printf("\n");
    }
    printf("%d", state->weight);
    free(array_of_state);
}

int main(){
    List *closed = queue();
    Queue *open = queue();
    char start[BOARD_SIZE];
    char goal[BOARD_SIZE];
    scanf("%9s", start);
    scanf("%9s", goal);
    State *solution = solve_puzzle(state(NULL, start), state(NULL, goal), open, closed);
    print_state(solution);
    free_all_state(solution);
    free_queue(open);
    free_queue(closed);
    return 0;
}
