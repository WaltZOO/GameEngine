# Parser

```haskell
Parser ::= (Automaton)* . <EOF>
```

## Automate

```haskell
Automaton ::= IDENTIFIER . State . "{" . (Mode)+ . "}"
```

## State

```haskell
State ::= "(" . StateID . ")"

StateID ::= 
  | IDENTIFIER
  | "_" 
  | epsilon
```

## Mode, Behaviour, Transition

```haskell
Mode ::=  "*" . State . (":")? . Behaviour

Behaviour ::= (Transition)*

Transition ::= ("|")? . Condition . "?" . (Actions)? . ":" . State
```

## Condition

```haskell 
Condition ::= Expression

Expression ::=
  | "(" . Expression . ")" . (BinOp_Expression)?
  | <UNARYOP> . Expression 
  | Predicate . (Binop_Expression) ?

Binop_Expression ::= 
  | <BINOP> . Expression

CondBinOp ::= "/" | "&"


Predicate ::=
  | "Key" . "(" . KeyName . ")"
  | CONDITION . (OptionalParameters)?

KeyName ::=
  | INTEGER
  | LOWERCASE
  | KEY
  | MOUSE
  | "_"

CONDITION ::=
  | "True" 
  | "MyDir" 
  | "Cell" 
  | "Closest" 
  | "Got" 
```

## Action

```haskell
Actions ::= Action . ( ActionBinOp . Action)*

ActionBinOp ::= "/" | ";"

Action ::= (Percent)? . ACTION . (OptionalParameters)?

Percent ::= INTEGER . "%"

ACTION ::= 
  | "Egg" 
  | "Explode" 
  | "Get"  
  | "Hit" 
  | "Jump" 
  | "Move" 
  | "Pick" 
  | "Pop"  
  | "Protect"
  | "Rest" 
  | "Store" 
  | "Throw" 
  | "Turn" 
  | "Wizz" 
  | "Wait"
```


## Parameters

```haskell
OptionalParameters ::= "(" . (Parameters)? . ")"

Parameters ::= Parameter . ("," . Parameter)*
     
Parameter ::=
  | Direction
  | Category  
  | INTEGER
```

## Direction

```haskell
Direction ::=
  | DIRECTION
  | "d"

DIRECTION ::= 
  | "Here"  | "H" 
  | "North" | "N" 
  | "South" | "S" 
  | "East"  | "E" 
  | "West"  | "W" 
  | "NE" | "SE" | "SW" | "NW" 
  | "Forward"  | "F" 
  | "BackWard" | "B" 
  | "Left"     | "L" 
  | "Right"    | "R"  
```

## Category

```haskell
Category ::= 
  | CATEGORY
  | "e" | "c" 
  | "_" 
```

## KEYS, MOUSE CONTROL

```haskell
KEY ::= 
  | "SPACE" 
  | "ENTER" 
  | "FU" 
  | "FD" 
  | "FL" 
  | "FR" 

MOUSE ::= 
  | "RightButtonPressed"  | "RBP"
  | "RightButtonReleaded" | "RBR"
  | "MidButtonPressed"    | "MBP"
  | "MidButtonReleased"   | "MBR" 
  | "LeftButtonPressed"   | "LBP"
  | "LeftButtonReleased"  | "LBR"
  | "WheelUp"             | "WLU"
  | "WheelDown"           | "WLD"         
```