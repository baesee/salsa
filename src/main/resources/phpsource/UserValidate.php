<?php
    $con = mysqli_connect("localhost", "billlog", "rhkdals12!", "billlog");

    $userId = $_POST["userId"];
    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userId = ?");
    mysqli_stmt_bind_param($statement, "s", $userId);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userId);

    $response = array();
    $response["success"] = true;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = false;
        $response["userId"] = $userId;
    }

    echo json_encode($response);

?>
