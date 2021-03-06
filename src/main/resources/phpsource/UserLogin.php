<?php
    $con = mysqli_connect("localhost", "billlog", "rhkdals12!", "billlog");

    $userId = $_POST["userId"];
    $userPassword = $_POST["userPassword"];

    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userId = ? AND userPassword = ?");
    mysqli_stmt_bind_param($statement, "ss", $userId, $userPassword);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userId);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["userId"] = $userId;
    }

    echo json_encode($response);
?>
