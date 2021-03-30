<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateInterventionsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('interventions', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('user_id');
            $table->foreign('user_id')
            ->references('id')->on('users')
            ->onDelete('cascade');
            $table->string('nomClient');
            $table->string('prenomClient');
            $table->string('adresseClient');
            $table->string('marqueChaudiere');
            $table->string('modeleChaudiere');
            $table->date('dateMiseEnService');
            $table->date('dateIntervention');
            $table->string('numeroSerie');
            $table->text('description');
            $table->bigInteger('tempsPasse');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('interventions');
    }
}
