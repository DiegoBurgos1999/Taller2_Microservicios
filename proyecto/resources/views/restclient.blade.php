<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cliente Rest</title>
    </head>
    <body>
        <h1>Cliente Rest</h1>

        <p>Author: {{$article->author}}</p>
        <p>Nombre articulo:{{$article->name}}</p>

    </body>
</html>
