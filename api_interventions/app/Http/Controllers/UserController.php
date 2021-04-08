<?php

namespace App\Http\Controllers;

use App\Models\User;
use App\Http\Resources\UserResource;
use App\Http\Requests\StoreUser;
use Illuminate\Http\Request;

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
    // return User::all();
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
   * Store a newly created resource in storage.
   *
   * @param  StoreUser  $request
   * @return \Illuminate\Http\Response
   */
  public function store(StoreUser $request)
  {
      $validated = $request->validated();
      User::create($validated);
  }

  /**
  * Update the specified resource in storage.
  *
  * @param  \Illuminate\Http\Request  $request
  * @param  \App\User  $user
  * @return \Illuminate\Http\Response
  */
  public function update(Request $request, User $user)
  {
    $user->update($request->all());
  }
  /**
  * Remove the specified resource from storage.
  *
  * @param  \App\User  $user
  * @return \Illuminate\Http\Response
  */
  public function destroy(User $user)
  {
    $user->delete();
  }
}
