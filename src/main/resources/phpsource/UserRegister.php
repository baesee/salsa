<?php
    $con = mysqli_connect("localhost", "billlog", "rhkdals12!", "billlog");

    $userId = $_POST["userId"];
    $userPassword = $_POST["userPassword"];
    $userGender = $_POST["userGender"];
    $userTeams = $_POST["userTeams"];
    $userEmail = $_POST["userEmail"];

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES(?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssss", $userId, $userPassword, $userGender, $userTeams, $userEmail);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);

?>
