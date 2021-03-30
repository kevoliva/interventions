<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use App\Http\Resources\UserResource;
use App\Http\Requests\StoreUser;

class UserController extends Controller
{

  public function __construct()
  {
    // rate limiting
    $this->middleware('throttle:60,1');
    $this->middleware(\Fruitcake\Cors\HandleCors::class);
  }

  /**
  * Display a listing of the resource.
  *
  * @return \Illuminate\Http\Response
  */
  public function index()
  {
    return UserResource::collection(User::all());
  }

  /**
  * Store a newly created resource in storage.
  *
  * @param  StoreUser $request
  * @return \Illuminate\Http\Response
  */
  public function store(StoreUser $request)
  {
    $validated = $request->validated();
    User::create($validated);
  }

  /**
  * Display the specified resource.
  *
  * @param  \App\Models\User  $user
  * @return \Illuminate\Http\Response
  */
  public function show(User $user)
  {
    return new UserResource($user);
  }

  /**
  * Update the specified resource in storage.
  *
  * @param  \Illuminate\Http\Request  $request
  * @param  \App\Models\User  $user
  * @return \Illuminate\Http\Response
  */
  public function update(Request $request, User $user)
  {
    //
  }

  /**
  * Remove the specified resource from storage.
  *
  * @param  \App\Models\User  $user
  * @return \Illuminate\Http\Response
  */
  public function destroy(User $user)
  {
    //
  }
}
