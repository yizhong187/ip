# Casper Chat User Guide

This is my individual project for CS2103T Software Engineering. 

## Introduction

Casper is an application designed to manage tasks efficiently through a Command Line Interface (CLI). It supports 
commands for task creation, modification, sorting, and listing tasks. The app is built to handle various types of 
tasks like `todo`, `deadline`, and `event`, providing comprehensive task management capabilities.

---
## Quick Start
### 1. Ensure Java 17 or higher is installed on your computer.
### 2. Download the latest `caspere.jar` file from [here](#).
### 3. Navigate to the folder where you downloaded the `.jar` file.
### 4. Run the application using the following command:
```bash
java -jar casper.jar
```
### 5. A command prompt will open, allowing you to enter commands such as `list`, `sort`, `find`, and more.
    Here are some example commands you can try:
- `list`: Lists all tasks.
- `todo buy milk`: Adds a new todo task with the description "buy milk".
- `mark 2`: Marks the second task as complete.
### 6. Refer to the Features section below for more details about each command.

---

## Features

### Command Format
- Words in **UPPER_CASE** are the parameters to be supplied by the user.  
  For example: `todo DESCRIPTION` requires a **DESCRIPTION**.
- Items in square brackets are optional.  
  Example: `todo DESCRIPTION [t/TAG]` can be used with or without a tag.
- Commands like `list` do not require additional parameters.
- Parameters can be in any order for commands like `add` or `edit`.

### Available Commands:

#### 1. `help`
Displays a list of all available commands.
`help`

#### 2. `list`
Lists all tasks in the current task list.
`list`

#### 3. `sort`
Sorts the task list by the specified method.
`sort /by [method]`
Available methods:
- `alphabetical`: Sorts tasks by alphabetical order.
- `type`: Sorts tasks by task type.
- `time`: Sorts tasks by their date or time.
- `status`: Sorts tasks by their completion status.

#### 4. `mark`
Marks a task as complete.
`mark TASK_INDEX`
Example:
`mark 2`

#### 5. `unmark`
Unmarks a task, indicating it is incomplete.
`unmark TASK_INDEX`
Example:
`unmark 2`

#### 6. `delete`
Deletes a task from the task list.
`delete TASK_INDEX`
Example:
`delete 3`

#### 7. `find`
Finds tasks containing the specified keyword.
`find KEYWORD`
Example:
`find homework`

#### 8. `todo`
Adds a new todo task.
`todo DESCRIPTION`
Example:
`todo Buy milk`

#### 9. `deadline`
Adds a new deadline task with a specific date.
`deadline DESCRIPTION /by DATE`
Example:
`deadline Submit assignment /by 2024-09-20`

#### 10. `event`
Adds a new event task with start and end times.
`event DESCRIPTION /from START_TIME /to END_TIME`
Example:
`event Attend conference /from 2024-09-20 10:00 /to 2024-09-20 12:00`

---

## Saving and Editing Data

- **Data is saved automatically** after every command that modifies the task list.
- Data is stored in a file `tasks.json` in the application's root directory.
- You can manually edit the `tasks.json` file, but be cautious as incorrect formats can lead to errors or data loss.

---
