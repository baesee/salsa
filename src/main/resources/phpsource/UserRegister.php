<?php
    $con = mysqli_connect("localhost", "billlog", "rhkdals12!", "billlog");

    $userID = $_POST["userID"];
    $userPassword = $_POST["userID"];
    $userGender = $_POST["userID"];
    $userMajor = $_POST["userID"];
    $userEmail = $_POST["$userEmail"];

    $statement = mysqli_prepare($con, "INSERT INTO USER VALUES(?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "sssss", $userID, $userPassword, $userGender, $userMajor, $userEmail);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);

?>