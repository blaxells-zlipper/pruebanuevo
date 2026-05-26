param(
    [Parameter(Mandatory = $true)]
    [string]$User,

    [Parameter(Mandatory = $false)]
    [string]$Password = "",

    [Parameter(Mandatory = $false)]
    [string]$DbHost = "localhost",

    [Parameter(Mandatory = $false)]
    [int]$Port = 3306,

    [Parameter(Mandatory = $false)]
    [string]$MySqlExe = "mysql"
)

$ErrorActionPreference = "Stop"

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
$schemaFile = Join-Path $scriptDir "schema-mysql.sql"
$dataFile = Join-Path $scriptDir "data-mysql.sql"

if (-not (Test-Path -LiteralPath $schemaFile)) {
    throw "Schema script not found: $schemaFile"
}

if (-not (Test-Path -LiteralPath $dataFile)) {
    throw "Data script not found: $dataFile"
}

$schemaPathForMysql = $schemaFile -replace "\\", "/"
$dataPathForMysql = $dataFile -replace "\\", "/"

$args = @(
    "--host=$DbHost",
    "--port=$Port",
    "--user=$User"
)

if ($Password -ne "") {
    $args += "--password=$Password"
}

Write-Host "Loading schema from: $schemaFile"
& $MySqlExe @args --execute="source $schemaPathForMysql"
if ($LASTEXITCODE -ne 0) {
    throw "Failed to load schema script. Check MySQL credentials and server status."
}

Write-Host "Loading data from: $dataFile"
& $MySqlExe @args --execute="source $dataPathForMysql"
if ($LASTEXITCODE -ne 0) {
    throw "Failed to load data script. Check MySQL credentials and schema state."
}

Write-Host "Done. Database PETCLINIC_DB is ready."
