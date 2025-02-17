package src.pieces;

import src.Piece;
import src.Position;
import src.PieceColor;

public class Knight extends Piece{

    public Knight(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if(newPosition.equals(position)) {
            return false;
        }

        int rowDiff = Math.abs(newPosition.getRow() - position.getRow());
        int colDiff = Math.abs(newPosition.getColumn() - position.getColumn());

        boolean validL = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        if(!validL) {
            return false;
        }
        //Check if we will capture a piece
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if(destinationPiece == null) {
            return true;
        }
        else {
            return destinationPiece.getColor() != this.color;
        }
    }
}
