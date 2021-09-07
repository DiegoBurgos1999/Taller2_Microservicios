<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Paseos</title>
    </head>
    <body>
        <h1>Paseos</h1>
        <div>
            @foreach ($paseos as $paseo)
                <p>{{ $paseo->identificador }}</p>
                <p>{{ $paseo->nombre }}</p>
                <p>{{ $paseo->lugar_salida }}</p>
                <p>{{ $paseo->lugar_llegada }}</p>
                <p>{{ $paseo->fecha }}</p>
            @endforeach
        </div>
    </body>
</html>
